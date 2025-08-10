#!/usr/bin/env -S clojure -Sdeps '{:deps {stencil/stencil {:mvn/version "0.5.0"}}}'

;; Instead, use pogonos: https://github.com/athos/pogonos/tree/master

(require '[stencil.core :refer [render-string]])
(println (render-string "Hi there, {{name}}." {:name "Donald"}))