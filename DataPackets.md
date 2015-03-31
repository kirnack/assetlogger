# DataPackets #

An abstract class that has children that holds different kinds of data. These DataPackets are send between views so that the views can know who the current user and requestor is. It also uses it to send checkout information.

When the view wants to perform a function it sends a request for a given function along with the data that function needs in a DataPacket object. The controller pulls up the needed function (which is also an abstract class with the children being the specific function to perform) and sets the DataPacket. The function then calls its doFunction() method and uses the data in the DataPacket to perform it.


We can get around using DataPackets by having this sort of information kept in a central store where both the view and the controller can access it.