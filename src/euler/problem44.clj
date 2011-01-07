(ns euler.problem44
  (:use euler.problem45))

;; this wasn't working fast enough...
(defn test-candidate [candidate]
  (and
   (pentagonal? candidate)
   (filter
    #(not (nil? %))
	   (for [p (pentagonal-numbers)
		 :while (< p candidate)]
	     (if (and
		  (pentagonal? (- candidate p))
		  (pentagonal? (Math/abs (- (- candidate p) (- p candidate))))
		  (not (= (Math/abs (- candidate p)) (Math/abs (- p candidate)))))
	       [candidate (- candidate p) (- p candidate)])))))


;; I think there is an easier way here:

(defn problem44 []
  (for [a pentagonal-numbers
	b (take-while #(> a %) pentagonal-numbers)
	:when (and
	       (pentagonal? (+ a b))
	       (pentagonal? (- a b)))]
    [a b]))
