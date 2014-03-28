package org.unittesting;

public interface EventLogger
{

    void delete(Event event);

    void save(Event event);

    void save(EventAudit eventAudit);

    public static class Impl implements EventLogger
    {
        @Override
        public void save(Event event)
        {
            simulateNetworkDelay();
            System.out.println("saved event: " + event);
        }

        @Override
        public void delete(Event event)
        {
            simulateNetworkDelay();
            System.out.println("deleted event: " + event);
        }

        @Override
        public void save(EventAudit eventAudit)
        {
            simulateNetworkDelay();
            System.out.println("saved event audit: " + eventAudit);
        }
        
        private void simulateNetworkDelay()
        {
            try
            {
                Thread.sleep(50);
            }
            catch(InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
