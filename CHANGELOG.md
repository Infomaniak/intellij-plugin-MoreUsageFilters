# More Usage Filters Changelog

## [Unreleased]

### Added

- `Show Test Usages` toggle. When it is off, every usage located in test sources (`test` and `androidTest` source
  sets) is hidden. Its toolbar icon is `@T`, echoing `@Test`.
- `Show Markdown Usages` toggle. When it is off, every usage located in a Markdown file, such as a README, is hidden,
  code fences included. Its toolbar icon is `M↓`, the Markdown mark.

### Removed

- `Show Comment Usages` toggle, Android Studio now ships its own filter for usages located in comments.

## [1.0.0]

### Added

- `Show Preview Usages` toggle in the Find Usages tool window and in the Show Usages popup. When it is off, every usage
  located inside a declaration annotated with a preview annotation (`@Preview`, multipreview annotations, custom
  `*Preview*` annotations) is hidden.
- `Show Comment Usages` toggle, next to it. When it is off, every usage located inside a comment is hidden, the
  `[Link]` references of a KDoc first and foremost.
- A dedicated toolbar icon for each toggle, `@P` and `/*`, traced from JetBrains Mono so that each one echoes the
  syntax it filters out, like the serif `i` of the import filter.
