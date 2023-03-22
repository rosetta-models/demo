Example 3:
Map xml elements with 2 attributes using value syntax
The attribute EngineSpecification->fuel->fuelType is mapped from xml path engineType->engineDetail->combustible:
-fuelType is mapped using only value "combustible" and since engineDetail node was consumed in Fuel
The attribute EngineSpecification->volumeUnit is mapped from xml path engineType->combustible->capacityUnit:
-volumeUnit is mapped using path "engineDetail" since its upper xml node combustible node has not been consumed yet and
needs to be in order to map it from capacityUnit at that level