# String HashSet
A simple java implementation of a String HashSet.


ClosedHashSet
-------------

Implementation of [Open addressing](https://en.wikipedia.org/wiki/Open_addressing). The basic idea is that hash collisions are resolved by searching through alternate locations in the array until either the target record is found, or an unused array slot is found.

OpenHashSet
-----------

Hash collisions are resolved by using a LinkedList in every array slot, and inserting/removing from the LinkedList. 

