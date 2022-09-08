// package com.example.demo2.business;

// import java.time.Duration;

// import io.github.bucket4j.Bandwidth;
// import io.github.bucket4j.Refill;

// public enum Info {

//     private int bucketCapacity = 5;
    
//     private Info(int bucketCapacity) {
//         this.bucketCapacity = bucketCapacity;
//     }
    
//     Bandwidth getLimit() {
//         return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofHours(1)));
//     }
    
//     public int bucketCapacity() {
//         return bucketCapacity;
//     }
    
    // public static Info resolvePlanFromApiKey(String apiKey) {
    //     if (apiKey == null || apiKey.isEmpty()) {
    //         return FREE;
        
    //     } else if (apiKey.startsWith("PX001-")) {
    //         return PROFESSIONAL;
            
    //     } else if (apiKey.startsWith("BX001-")) {
    //         return BASIC;
    //     }
    //     return FREE;
    // }
//}