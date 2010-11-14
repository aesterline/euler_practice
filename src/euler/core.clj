(ns euler.core
  (:require [clojure.contrib.lazy-seqs :as lazy]
            [clojure.contrib.math :as math]
            [clojure.string :as string]))

(defn divides? [dividend divisor]
  (zero? (rem dividend divisor)))

(defn divides-any [& divisors]
  (fn [dividend]
    (boolean (some #(divides? dividend %) divisors))))

(defn divides-all [& divisors]
  (fn [dividend]
    (every? #(divides? dividend %) divisors)))

(defn palindrome? [num]
  (let [word (seq (str num))]
    (= word (reverse word))))

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

(defn smallest-divisible-by-all []
 (let [candidates (iterate #(+ % (* 19 20)) (* 19 20))
       divisible? (apply divides-all (range 11 19))]
   (first (filter #(divisible? %) candidates))))

(defn diff-sum-of-squares-and-square-of-sums []
  (let [square (fn [x] (* x x))
        one-hundred (range 1 101)
        sum-of-squares (reduce + (map square one-hundred))
        square-of-sums (square (reduce + one-hundred))]
    (- square-of-sums sum-of-squares)))

(defn prime-at
  ([] (prime-at 10001))
  ([n]
   (nth lazy/primes (dec n))))

(defn sum-of-primes-below
  ([] (sum-of-primes-below 2000000))
  ([max]
   (->>
     lazy/primes
     (take-while #(< % max))
     (reduce +))))

(defn largest-palindrome []
  (->> (for [n1 (range 100 1000) 
             n2 (range 100 n1)] (* n1 n2))
       (filter palindrome?)
       (apply max)))

(def number-string
  (string/trim-newline (slurp "data/problem8.txt")))

(defn euler-8 []
  (let [number-sequence (map #(Character/getNumericValue %) (seq number-string))]
    (->> (partition 5 1 number-sequence)
         (map #(reduce * %))
         (apply max))))
