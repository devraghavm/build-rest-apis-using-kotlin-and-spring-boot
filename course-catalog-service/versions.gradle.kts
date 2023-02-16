mapOf(
    "kotlinLoggingVersion" to "3.0.5",
    "mockkVersion" to "1.13.4",
    "springMockkVersion" to "4.0.0",
    "testContainersVersion" to "1.17.6"
).forEach { (name, version) ->
    project.extra.set(name, version)
}