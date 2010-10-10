(ns solutions.euler
  (:use [clojure.contrib.lazy-seqs])
  (:use [clojure.contrib.combinatorics])
  (:use [clojure.set]))
;; problem 1

(defn divides?
  "Does a divide b evenly?"
  [a b]
  (if (= (rem a b) 0)
    true
    false))

(defn divides-any?
  "Does a divide any of b evenly?"
  [& nums]
  (fn [arg]
    (boolean (some #(divides? arg %) nums))))

(defn problem-1-recur
  "Sum the numbers divisible by 3 or 5, from 0 to upper."
  ([]
     (problem-1-recur 1000))
  ([upper]
     (let [divisible? (divides-any? 3 5)]
       (loop [sum 0 n 1]
	 (if (>= n upper)
	   sum
	   (recur
	    (if (divisible? n) (+ sum n) sum)
	    (inc n)))))))

(defn problem-1
  ([]
   "Sum the numbers divisible by 3 or 5, from 0 to upper."
     (problem-1 1000))
  ([upper]
     (apply +
	    (filter
	     (divides-any? 3 5)
	     (range upper)))))

(defn problem-1-left-to-right
  ([]
   "Sum the numbers divisible by 3 or 5, from 0 to upper."
   (problem-1-left-to-right 1000))
  ([upper]
     (->> (range upper)
	  (filter (divides-any? 3 5))
	  (apply +))))

;; problem 2

(defn fibos  
  []
  (map first
       (iterate
	(fn [[a b]] [b (+ a b)]) [0 1])))
  

(defn sum-even-fibos
  ([]
     "Find the sum of all the even-valued terms in the fibonacci sequence which do not exceed four million."
     (sum-even-fibos 4000000))
  ([limit]
     (->> (fibos)
	  (take-while #(< % limit))
	  (filter even?)
	  (apply +))))

;; problem 3

(defn primes-until
  [until]
  "Find all of the primes until a number"
  (for [p primes :while (<= p until)] p))


(defn prime-divisors
  [n]
  "Find the prime divisors of n"
  (filter #(divides? n %) (primes-until (Math/sqrt n))))

(defn problem-3
  ([]
     "Find the greatest prime divisor of 600851475143"
     (problem-3 600851475143))
  ([n]
     (->> (primes-until (Math/sqrt n))
	  (filter #(divides? n %))
	  (last))))

;; problem 4

;; note: (str 4) -> "4"

(defn palindromic?
  [n]
  (if (= (str n) (apply str (reverse (str n))))
    true
    false))

(defn products-of-3-digit-numbers
  []
  (for [x (range 100 999) y (range 100 999)] (* x y))) 

(defn problem-4
  ([]
     "Find the largest palindrome made from the product of two 3-digit numbers."
     (apply max (filter palindromic? (products-of-3-digit-numbers)))))

;; problem 5

;; (for [d (range 1 20) n (solutions.euler/positive-integers)] (solutions.euler/divides? d n))

(defn even-positive-integers
  []
  (iterate #(+ 2 %) 2))

(defn problem-5
  []
  "What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?"
  (first (filter (fn [n] (every? #(zero? (rem n %)) (range 2 20))) (even-positive-integers))))

;; "textbook" solution:

(defn problem-5-soln
  "2520 is the smallest number that can be divided by each of the numbers
   from 1 to 10 without any remainder.

   What is the smallest number that is evenly divisible by all of the numbers
   from 1 to 20?"
  ([] (problem-5-soln 20))
  ([upper]
     (first (let [divisors (range 2 (inc upper))]
              (filter
               (fn [n] (every? #(zero? (rem n %)) divisors))
               (iterate inc 2))))))


;;
;; Problem 24
;;

;; A permutation is an ordered arrangement of objects. For example, 3124
;; is one possible permutation of the digits 1, 2, 3 and 4. If all of the
;; permutations are listed numerically or alphabetically, we call it
;; lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

;; 012   021   102   120   201   210

;; What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

;; well, that one was easy:

(defn problem-24
  []
  (time (nth (lex-permutations [0 1 2 3 4 5 6 7 8 9]) 999999)))


;; A perfect number is a number for which the sum of its proper divisors
;; is exactly equal to the number. For example, the sum of the proper
;; divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28
;; is a perfect number.

;; A number n is called deficient if the sum of its proper divisors is
;; less than n and it is called abundant if this sum exceeds n.

;; As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the
;; smallest number that can be written as the sum of two abundant numbers
;; is 24. By mathematical analysis, it can be shown that all integers
;; greater than 28123 can be written as the sum of two abundant
;; numbers. However, this upper limit cannot be reduced any further by
;; analysis even though it is known that the greatest number that cannot
;; be expressed as the sum of two abundant numbers is less than this
;; limit.

;; Find the sum of all the positive integers which cannot be written as
;; the sum of two abundant numbers.

(defn integers-until
  [^Integer until]
  "Integers until a specified value"
  (range 1 (+ 1 until)))

(defn integer-divisors
  [^Integer n]
  "Find the integer divisors of n"
  (filter
   #(divides? n %)
   (integers-until (/ n 2))))

(defn sum-integer-divisors
  [^Integer n]
  "Calculate the sum of the integer divisors of n"
  (reduce + (integer-divisors n)))

(defn abundant?  
  [^Integer n]
  "Is n abundant?"
  (if (> (sum-integer-divisors n) n)
    true
    false))
     
(defn abundants
  [^Integer n]
  "List the abundant numbers until n"
    (filter
     #(abundant? %)
     (integers-until n)))

(defn sum-of-two-abundants?
  [^Integer n]
  "Can n be expressed as the sum of two abundant numbers?"
  (let [s2a (sums-of-two-abundants n)]
      (some #(= n %) s2a)))

(defn sums-of-two-abundants
  [^Integer n]
  "List numbers that are sums of two abundant numbers until n"
  (filter #(<= % n)
	  (set
	   (pmap #(reduce + %) (combinations (concat (abundants n) (abundants n)) 2)))))

(defn problem-23-imp [^Integer n]
  (reduce +
	  (difference
	   (set (range 1 (+ 1 n)))
	   (set (sums-of-two-abundants n)))))

(defn problem-23
  []
  (problem-23-imp 28123))
