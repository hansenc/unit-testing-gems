package org.unittesting;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Sets;

import org.assertj.core.api.Condition;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

@Ignore
public class AssertJFailuresTest
{

    Product monitor = new Product(1, "Monitor");

    Product wallet = new Product(2, "Wallet");

    @Test
    public void failSize()
    {
        Set<Product> products = Sets.newHashSet(wallet, monitor);
        assertThat(products).hasSize(1);
    }

    @Test
    public void failName()
    {
        Set<Product> products = Sets.newHashSet(wallet, monitor);
        assertThat(products).extracting("name").isEqualTo("Trombone");
    }

    @Test
    public void failString()
    {
        assertThat("Test string").startsWith("Test").doesNotHave(suffix("string"));
    }

    private Condition<? super String> suffix(final String suffix)
    {
        return new Condition<String>("suffix \"" + suffix + "\"")
        {
            @Override
            public boolean matches(String value)
            {
                return value.endsWith(suffix);
            }
        };
    }

    private class Product
    {
        long id;

        String name;

        private Product(long id, String name)
        {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
