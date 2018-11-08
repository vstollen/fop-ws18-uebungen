;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H02_Stollenwerk_Vincent) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(require 2htdp/image)

;; Reference matrices: http://biecoll.ub.uni-bielefeld.de/volltexte/2007/52/pdf/ICVS2007-6.pdf
(define m-rgb-to-lms (list (list 17.8824 43.5161 4.1193) (list 3.4557 27.1554 3.8671) (list 0.02996 0.18431 1.4670)))
(define m-lms-to-lms-protanopia (list (list 0 2.02344 -2.52581) (list 0 1 0) (list 0 0 1)))
(define m-lms-to-rgb (list (list 0.0809 -0.1305 0.1167) (list -0.0102 0.0540 -0.1136) (list -0.0003 -0.0041 0.6935)))

;; H1 helper function
;; Type: (list of (list of number)) -> (list of (list of number))
;; Precondition: All matrix rows must have the same amount of entries,
;;               the list should represent a matrix from H02
;;               (all inner lists must have the same amount of entries)
;; Returns: The columns of a matrix
(define (get-matrix-columns matrix)
  (if (empty? matrix)
      empty
      (if (empty? (first matrix))
          empty
          (cons (map first matrix) (get-matrix-columns (map rest matrix))))))

(check-expect (get-matrix-columns (list (list 1 2 5) (list 3 4 6) (list 8 9 10))) (list (list 1 3 8) (list 2 4 9) (list 5 6 10)))
(check-expect (get-matrix-columns empty) empty)
(check-expect (get-matrix-columns (list (list 1) (list 2) (list 3))) (list (list 1 2 3)))

;; H1 helper function from V3
;; Type: (list of number) (list of number) -> number
;; Precondition: Both lists have the same length
;; Returns: Scalar of two vectors
(define (vec-mult lst-a lst-b)
  (foldr + 0 (map * lst-a lst-b)))

(check-expect (vec-mult (list 1 2 3) (list 4 5 6)) 32)
(check-expect (vec-mult (list 1 0 1) (list 0 1 0)) 0)
(check-expect (vec-mult (list -5 2 1) (list 3 -8 5)) -26)

;; H1
;; Type: (list of (list of number)) (list of (list of number)) -> (list of (list of number))
;; Precondition: The matrices must be multiplyable
;;               (M1 column count must match M2 row count)
;; Returns: The multiplication product of two matrices
(define (matrix-multiplication m1 m2)
  ;; Type: (list of number) -> (list of number)
  ;; Returns: Matrix multiplication of a given row with the second matrix
  (map (lambda (matrix-row)
         ;; Type: (list of number) -> number
         ;; Returns: Scalar product of the matrix row with a given matrix column
         (map (lambda (matrix-column)
                (vec-mult matrix-row matrix-column))
              (get-matrix-columns m2)))
       m1))

(check-expect (matrix-multiplication (list (list 4 3) (list 6 3)) (list (list 1 2 3) (list 4 5 6))) (list (list 16 23 30) (list 18 27 36)))
(check-expect (matrix-multiplication (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5)) (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5))) (list (list 12 -3 -37) (list 1 -2 1) (list -9 -3 40)))
(check-expect (matrix-multiplication empty empty) empty)

;; H2
;; Type: color -> (list of (list of number))
;; Returns: A 3-dimensional vector with the rgb value of the given color
(define (color->vector clr)
  (list (list (color-red clr)) (list (color-green clr)) (list (color-blue clr))))

(check-expect (color->vector (make-color 1 2 3 255)) (list (list 1) (list 2) (list 3)))
(check-expect (color->vector (make-color 255 182 40 10)) (list (list 255) (list 182) (list 40)))
(check-expect (color->vector (make-color 0 0 0 0)) (list (list 0) (list 0) (list 0)))

;; Type: (list of (list of number)) -> color
;; Precondition: Numbers have to be exact numbers
;; Returns: Color given a 3-dimensional vector
(define (vector->color vec)
  ;; Type: (list of number) -> color
  ;; Returns: A color constructed with the rgb values of a given list with 3 elements (rgb)
  ((lambda (rgb-list)
    (make-color (first rgb-list) (first (rest rgb-list)) (first (rest (rest rgb-list))) 255))
   ;; Type: (list of number) -> natural
   ;; Precondition: Numbers have to be exact
   ;; Returns: First entry of given list, roundet down to the next natural number,
   ;;          if it's smaller than 0 returns 0,
   ;;          if it's greater than 255 returns 255
   (map (lambda (vector-entry)
         (if (< (first vector-entry) 0)
             0
             (if (> (first vector-entry) 255)
                 255
                 (floor (first vector-entry)))))
       vec)))

(check-expect (vector->color (list (list 1) (list 2) (list 3))) (make-color 1 2 3 255))
(check-expect (vector->color (list (list -10) (list (/ 4 3)) (list -100))) (make-color 0 1 0 255))
(check-expect (vector->color (list (list 1000) (list -5) (list 42.5))) (make-color 255 0 42 255))

;; H3
;; Type: (list of (list of number)) -> (list of (list of number))
;; Returns: Protanopia simulation for a single pixel
(define (rgb-vector-protanopia vec)
  (matrix-multiplication m-lms-to-rgb (matrix-multiplication m-lms-to-lms-protanopia (matrix-multiplication m-rgb-to-lms vec))))

(check-expect (rgb-vector-protanopia (list (list 0) (list 0) (list 0))) (list (list 0) (list 0) (list 0)))
(check-within (rgb-vector-protanopia (list (list 180) (list 240) (list 75))) (list (list 232.6) (list 233.8) (list 75.8)) 0.1)
(check-within (rgb-vector-protanopia (list (list 65) (list 198) (list 42))) (list (list 182.5) (list 183.4) (list 42.3)) 0.1)

;; H4 helper function
;; Type: (list of color) -> (list of color)
;; Returns: List after simulating protanopia on all entries
(define (protanopia-recursive-color-list lst)
  (if (empty? lst)
      empty
      (cons (vector->color (rgb-vector-protanopia (color->vector (first lst)))) (protanopia-recursive-color-list (rest lst)))))

;; H4
;; Type: image -> image
;; Returns: Given image after simulating protanopia
(define (protanopia-recursive img)
  (color-list->bitmap (protanopia-recursive-color-list (image->color-list img)) (image-width img) (image-height img)))

(define input (bitmap/file "rainbow_colors.png"))
(define protanopia (protanopia-recursive input))
(define solution (bitmap/file "solution_rainbow_colors.png"))
(check-expect protanopia solution)
(save-image protanopia "out_rainbow_colors.png")


;; H5
;; Type:
;; Returns:
;;(define (protanopia-map img)
  ;; insert code here
;;)


;; H6
;; Types:
;; Returns:
;;(define (image-similarity img1 img2 max-difference)
  ;; insert code here
;;  )
