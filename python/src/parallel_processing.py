
import heapq


def parallel_processing(threads, tasks):
    """
    You have a program which is parallelized and uses n independent threads to process the given list
    of m jobs. Threads take jobs in the order they are given in the input. If there is a free thread,
    it immediately takes the next job from the list. If a thread has started processing a job, it doesn’t
    interrupt or stop until it finishes processing the job. If several threads try to take jobs from the list
    simultaneously, the thread with smaller index takes the job. For each job you know exactly how long
    will it take any thread to process this job, and this time is the same for all the threads. You need to
    determine for each job which thread will process it and when will it start processing.
    """

    heap = []
    processing_result = []
    task_index = 0
    available_threads = [(0, t) for t in range(threads)]

    def __take_tasks(heap, task_index):
        for start_time, thread in available_threads:
            if task_index >= len(tasks):
                break
            heapq.heappush(heap, (start_time + tasks[task_index], thread))
            processing_result.append((thread, start_time))
            task_index += 1
        available_threads.clear()

    def __run_tasks(heap):
        top_time, top_thread = heapq.heappop(heap)
        available_threads.append((top_time, top_thread))
        while heap and heap[0][0] == top_time:
            time, thread = heapq.heappop(heap)
            available_threads.append((time, thread))

    while task_index < len(tasks):
        tc = len(available_threads)
        __take_tasks(heap, task_index)
        task_index += tc
        __run_tasks(heap)

    return processing_result
