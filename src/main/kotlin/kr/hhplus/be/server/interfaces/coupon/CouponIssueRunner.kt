package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponCriteria
import kr.hhplus.be.server.application.coupon.CouponFacade
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledFuture

@Component
class CouponIssueRunner(
    private val couponFacade: CouponFacade,
    private val taskScheduler: TaskScheduler
) {
    private val taskMap: MutableMap<Long, ScheduledFuture<*>> = ConcurrentHashMap()

    fun start(eventId: Long, cronExpression: String) {
        if (taskMap.containsKey(eventId)) return
        val task = Runnable { couponFacade.processIssue(CouponCriteria.Process(eventId, 10)) }
        val future = (taskScheduler as ThreadPoolTaskScheduler).schedule(task, CronTrigger(cronExpression))
        if (future != null) taskMap[eventId] = future
    }

    fun stop(eventId: Long) {
        taskMap[eventId]?.cancel(false)
        taskMap.remove(eventId)
    }

    fun isRunning(eventId: Long): Boolean = taskMap[eventId]?.isCancelled == false
}