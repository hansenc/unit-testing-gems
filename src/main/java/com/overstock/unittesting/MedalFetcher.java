package com.overstock.unittesting;

public interface MedalFetcher
{
    Medal getMedal(CompetitionEvent competitionEvent);

    public static class Impl implements MedalFetcher
    {

        static final Medal GOLD = Medal.GOLD;

        @Override
        public Medal getMedal(CompetitionEvent competitionEvent)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("going accross the net to " + competitionEvent + "to get data for nic");
            return GOLD;
        }

    }
}
