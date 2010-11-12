(ns euler.problem19)

;; You are given the following information, but you may prefer to do
;; some research for yourself.

;; 1 Jan 1900 was a Monday.  Thirty days has September, April, June
;; and November.  All the rest have thirty-one, Saving February alone,
;; Which has twenty-eight, rain or shine.  And on leap years,
;; twenty-nine.  A leap year occurs on any year evenly divisible by 4,
;; but not on a century unless it is divisible by 400.  How many
;; Sundays fell on the first of the month during the twentieth century
;; (1 Jan 1901 to 31 Dec 2000)?

(def days-of-week
     '("Mon" "Tue" "Wed" "Thu" "Fri" "Sat" "Sun"))

(def normal-year
     (concat (range 1 32) ;; jan
	     (range 1 29) ;; feb
	     (range 1 32) ;; mar
	     (range 1 31) ;; apr
	     (range 1 32) ;; may
	     (range 1 31) ;; june
	     (range 1 32) ;; july
	     (range 1 32) ;; august
	     (range 1 31) ;; sep
	     (range 1 32) ;; oct
	     (range 1 31) ;; nov
	     (range 1 32))) ;; dec

(def leap-year
     (concat (range 1 32)
	     (range 1 30)
	     (range 1 32)
	     (range 1 31)
	     (range 1 32)
	     (range 1 31)
	     (range 1 32)
	     (range 1 32)
	     (range 1 31)
	     (range 1 32)
	     (range 1 31)
	     (range 1 32))) 

(def century-dom
     (flatten (repeat 25 (concat (repeat 3 normal-year) leap-year))))

;; TODO: what's wrong here?

(def problem-19
     (count (filter #(= % '("Sun" 1))
		    (partition 2 2 (interleave
				    (cycle days-of-week)
				    century-dom)))))

problem-19