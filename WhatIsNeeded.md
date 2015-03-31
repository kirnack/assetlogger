# Server Side #

  * Sanitization of strings for SQL statements.
  * We might add an extra field for the label of an asset to distinguish it apart from the rentals vs checkouts

# Controller #
  * Still needs to be able to handle adding and removing individual laptops from a request
  * Implement student rental ability
  * Review of the design: make improvements
> Use an object that any view can access so that it can know who the current user is and who the current requestor is. This will avoid having to send DataPackets everywhere.
  * We should have views send information to some object for storing data that the Function objects can access so that we can get rid of DataPacket sending alltogether

# View #
  * The GUI needs rework so that it matches the needs of our user
  * Review of the design: make improvements


## Requirements Issue ##

  * We have a conflict in the requirements. We need to decide when to make an asset available for use. We of course want to make it so that requests can be made far in advance. We also don't want for a teacher to bring it back late and cause another teacher to not get one. There has been some discussion about charging a fine, which would alleviate the issue.

# In a nutshell this is what is needed for Winter 2011: #
  * Implement the ability to swap out individual assets
  * Get it networked
  * Work out bugs
  * Get a fully documented design
  * Hook up our database with the schools (which we will only have read access for)
  * Optional: get the ability to rent student laptops