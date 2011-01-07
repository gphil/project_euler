(ns euler.problem31)

;; In England the currency is made up of pound, £, and pence, p, and
;; there are eight coins in general circulation:

;; 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).  It is possible
;; to make £2 in the following way:

;; 1£1 + 150p + 220p + 15p + 12p + 31p How many different ways can £2
;; be made using any number of coins?

;; need to find all x1...x7 that satisfy:
(defn test-values [x1 x2 x3 x4 x5 x6 x7]
  (= 200
     (+
      (* x1 1)
      (* x2 2)
      (* x3 5)
      (* x4 20)
      (* x5 50)
      (* x6 100)
      (* x7 200))))

;; semi-brute-force strategy: test x1 to 200, x2 to 100, x3 to 40, x4
;; to 10, x5 to 4, x6 to 2, x7 to 1.  (* 200 100 40 10 5 4 2 1) --
;; 320000000 combinations

;; better strategy: nested for loops guarded using :when clauses,
;; starting from the smallest going to the biggest--this should pare
;; down the number of solns tested.

(defn bust? [x1 x2 x3 x4 x5 x6 x7]
  (< 200
     (+
      (* x1 1)
      (* x2 2)
      (* x3 5)
      (* x4 20)
      (* x5 50)
      (* x6 100)
      (* x7 200))))

(defn problem31 []
  (+ 1 ;; +1 for 1x200, which we don't need to bother testing
     (count
    (for [x1 (range 0 201) :when (not (bust? x1 0 0 0 0 0 0)) 
	  x2 (range 0 101) :when (not (bust? x1 x2 0 0 0 0 0)) 
	  x3 (range 0 41) :when (not (bust? x1 x2 x3 0 0 0 0)) 
	  x4 (range 0 11) :when (not (bust? x1 x2 x3 x4 0 0 0)) 
	  x5 (range 0 5) :when (not (bust? x1 x2 x3 x4 x5 0 0))
	  x6 (range 0 3) :when (not (bust? x1 x2 x3 x4 x5 x6 0))
	  :when (test-values x1 x2 x3 x4 x5 x6 0)]
      [x1 x2 x3 x4 x5 x6]))))

;; need to rethink this one.