package com.bing.study1.lock.source;

public class BingReentrantLock {
    final BingAQS aqs = new Sync();
    class Sync extends BingAQS {
        @Override
        public boolean tryAcquire() {
            if(isCurrent()){
                setState(getState()+1);
//                System.out.println(owner.get().getName()+"-----------"+getState()+"-----------");
                return true;
            }else{
                if(owner.compareAndSet(null, Thread.currentThread())){
                    setState(getState()+1);
//                    System.out.println(owner.get().getName()+"-----------"+getState()+"-----------");
                    return true;
                }else{
                    return false;
                }
            }
        }

        @Override
        public boolean tryRelease() {
            setState(getState()-1);
//            System.out.println(owner.get().getName()+"============"+getState()+"============");
            if(getState() == 0){
                return owner.compareAndSet(Thread.currentThread(), null);
            }
            return true;
        }
    }

    public void lock(){
        aqs.acquire();
    }

    public void unlock(){
        aqs.release();
    }

    public int getHoldCount(){
        return aqs.getState();
    }
}
