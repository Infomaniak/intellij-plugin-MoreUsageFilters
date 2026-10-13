# Agent guidelines

- Write new code in Kotlin, including tooling and scripts (see `tools/icon-generator`, a Gradle source set run by the
  `generateIcon` task). Avoid adding Java, shell or Python tooling.
- Generate toolbar icons with `./gradlew -q generateIcon --args='<text> src/main/resources/icons/<name>'` rather than
  drawing them by hand.
- Record user-facing changes in `CHANGELOG.md` under `## [Unreleased]`, it feeds the plugin's change notes.
- Run `./gradlew check buildPlugin` before committing.
