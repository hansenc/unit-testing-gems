package org.unittesting;

import java.lang.reflect.Method;
import java.util.Set;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class EventAnswer implements Answer<Event>{
  private static final String GETTER_PREFIX = "get";

  private static final String IS_PREFIX = "is";
  
  final Set<String> getters;
  
  final Set<String> invokedGetters = Sets.newHashSet();
  
  public EventAnswer() {
    Set<String> getters = Sets.newHashSet();
    Class<Event> lineItemClass = Event.class;
    Method[] declaredMethods = lineItemClass.getDeclaredMethods();
    for (Method eachMethod : declaredMethods) {
      if (eachMethod.getName().startsWith(GETTER_PREFIX) || eachMethod.getName().startsWith(IS_PREFIX)) {
        getters.add(eachMethod.getName());
      }
    }
    this.getters = ImmutableSet.copyOf(getters);
  }
  
//  @Override
  public Event answer(InvocationOnMock invocation) throws Throwable {
    System.out.println("invoked: " + invocation.getMethod().getName());
    Method method = invocation.getMethod();
    if (method.getName().startsWith(GETTER_PREFIX)|| method.getName().startsWith(IS_PREFIX)) {
      invokedGetters.add(method.getName());
    }
    return null /*don't care about returning anything, just that all getters are invoked*/;
  }

  public boolean isMissingGetInvocations() {
    return !getMissingGetInvocations().isEmpty();
  }

  public Set<String> getMissingGetInvocations() {
    Set<String> copyOfGetters = Sets.newHashSet(getters);
    copyOfGetters.removeAll(invokedGetters);
    return copyOfGetters;
  }
  
}
