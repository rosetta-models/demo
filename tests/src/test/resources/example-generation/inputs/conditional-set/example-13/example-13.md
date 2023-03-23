Example 13:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path with multi-cardinality.
The attribute EngineSpecification is conditionally set from xml path engine->engineType->engineDetail when the value at
xml path engineType->engineDetail->combustible = "Petrol" 