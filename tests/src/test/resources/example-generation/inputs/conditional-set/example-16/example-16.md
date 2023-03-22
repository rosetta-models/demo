Example 16:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path and rosettaPath with multi-cardinality and meta.
The attribute EngineSpecification->fuel->fuelType is conditional set from xml path engineType->engineDetail->combustible
when
"engineDetail->combustible->scheme"="petrolScheme" in xml and Root->engineSpecification->fuel->fuelType in rosettaPath 