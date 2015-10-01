package br.com.julio;

import org.apache.log4j.BasicConfigurator;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Executa {
	
	public static void main(String[] args) {
		try {
			BasicConfigurator.configure();

			//agendador
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			
			//apresenta o job
			JobDetail job = JobBuilder.newJob(Produtor.class).withIdentity("QuickQuartzJob").build();
			ScheduleBuilder<?> scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");

			//gatilho - inicia o job
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("QuickQuartzTrigger")
					.withSchedule(scheduleBuilder).startNow().build();
			
			scheduler.scheduleJob(job, trigger);
						
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
