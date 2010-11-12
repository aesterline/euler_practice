(ns euler.core
  (:require [clojure.contrib.lazy-seqs :as lazy]
            [clojure.contrib.math :as math]))

(defn divides? [dividend divisor]
  (zero? (rem dividend divisor)))

(defn divides-any [& divisors]
  (fn [dividend]
    (boolean (some #(divides? dividend %) divisors))))

(defn sum-of-numbers-divisible-by-3-or-5
  ([] (sum-of-numbers-divisible-by-3-or-5 1000))
  ([max]
   (->>
     (range max)
     (filter (divides-any 3 5))
     (reduce +))))

(defn even-fib-sum-less-than
  ([] (even-fib-sum-less-than 4000000))
  ([max]
   (->>
     (take-while #(< % max) (lazy/fibs))
     (filter even?)
     (reduce +))))

(defn largest-prime-factor-of
  ([] (largest-prime-factor-of 600851475143))
  ([num]
   (let [max-prime-factor (int (math/sqrt num))]
     (->>
       (take-while #(< % max-prime-factor) lazy/primes)
       (reverse)
       (filter #(zero? (rem num %)))
       (first)))))
