Example 2:
Map two xml elements on to single rosetta type using value and path syntax
The attribute EngineSpecification->volume->volumeUnit is mapped from xml path engineType->engineDetail->capacityUnit:
-volumeUnit is mapped using path "engineDetail" since its upper xml node engineDetail has not been consumed yet and
needs to be in order to map it from capacityUnit at that level
The attribute EngineSpecification->fuelType is mapped from xml path engineType->combustible->combustibleType:
-fuelType is mapped using path "combustible" since its upper xml node combustible has not been consumed yet and needs to
be in order to map it from combustibleType at that level