(ns euler.test.core
  (:use [euler.core])
  (:use [lazytest.describe :only (describe it given)]))

(describe divides?
  (it "should return true for numbers that divide evenly" 
      (divides? 9 3))
  (it "should return false for numbers that don't divide evenly"
      (not (divides? 8 3))))

(describe divides-any
  (given [evenly-divisible? (divides-any 3 5)]
    (it "should return true when divisible by 3"
         (evenly-divisible? 9))
    (it "should return true when divisible by 5"
        (evenly-divisible? 10))
    (it "should return true when divisible by 3 and 5"
        (evenly-divisible? 15))
    (it "should return false when not divisible by either 3 or 5"
        (not (evenly-divisible? 14)))))

(describe divides-all
  (given [all (divides-all 3 5)]
    (it "should return true when divisible by all"
        (all 15))
    (it "should return false when divisible by only one"
        (not (all 10)))))

