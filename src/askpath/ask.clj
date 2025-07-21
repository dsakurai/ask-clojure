(ns askpath.ask
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            )
  (:import (com.networknt.schema JsonSchemaFactory SpecVersion$VersionFlag JsonSchema ValidatorTypeCode)
           (com.fasterxml.jackson.databind ObjectMapper))
  )

(defn validate-schema [schema-stream json-stream]
  (let [mapper (ObjectMapper.)
        factory (JsonSchemaFactory/getInstance SpecVersion$VersionFlag/V7)
        schema (.getSchema factory schema-stream)
        node (.readTree mapper json-stream)
        errors (.validate schema node)
        ]
    {:errors errors :node node}
  ))

(defn -main []
  (let [snippets-file-env (System/getenv "ASK_SNIPPET_FILE")]
    (let [snippets-file-path (if (nil? snippets-file-env) "snippets.json" snippets-file-env)
          snippets-json-str (slurp snippets-file-path)
          result (validate-schema
                    (slurp "snippets-schema.json") ;; Might be slower than (io/input-stream "filename")
                    snippets-json-str
                   )
          errors (:errors result)
          snippets-map (json/parse-string snippets-json-str)
          ]
      (if (.isEmpty errors)
        (do
          (println (get snippets-map "entries"))
          (println (System/getenv "ASK_SNIPPET_FILE"))
          )
        ;; Print errors
        (do
          (println "JSON file does not follow the schema:")
          (doseq [err errors]
            (println (.getMessage err))))))
   ))

