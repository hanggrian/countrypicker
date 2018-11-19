include(RELEASE_ARTIFACT)
include("$RELEASE_ARTIFACT-sheet")
includeDir("demo")

fun includeDir(dir: String) = File(dir)
    .walk(FileWalkDirection.BOTTOM_UP)
    .filter { it.isDirectory }
    .forEach { include("$dir:${it.name}") }