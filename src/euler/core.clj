(ns euler.core)

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
