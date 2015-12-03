## Contribution Guidelines

Contributions are welcome! For a great PR, please consider:

### Workflow
* Fork the repository then check out the code
* Run the tests with ```./gradlew tests:test``` to make sure they pass
* Implement your feature
* Write Javadoc for public API (if applicable)
* Write tests (if applicable)
* Make sure all the tests pass
* Check whitespace problems with ```git diff --check```
* Commit
* Submit a pull request

### Style
* When coding and documenting, follow the coding style and whitespace settings applied in the project
* While one does not have to agree with everything in it, [Clean Code](https://www.google.hu/search?q=clean+code) is a really great resource. At the very least:
  * Code should be self-expressive with good variable and method names
  * Split longer methods into several smaller ones with meaningful names when needed
  * Try to stay on the same level of abstraction within a method
* Write a [good commit message](http://chris.beams.io/posts/git-commit/)
* Commits should contain logically connected changes described by the commit message. More than one step is required to implement the feature? Do not use one "Fixes everything and stuff and also lorem ipsum" commit. Use as many commits as required, each with their own meaningful commit message.
