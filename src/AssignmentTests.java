import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

// These tests illustrate some basic properties of the methods you have been asked
// to implement. They are not exhaustive.
// For assessment there will also be cases where various combinations of
// the methods will be tested. You are strongly recommended to create some tests for
// yourself to ensure that your implementations satisfy all the specifications. 


public class AssignmentTests {
	@Test
	public void testSearchForwards() {
		
		String[] items = {"a","b","c","d","e","f"};
		ArrayList<DNode> list = createList(items); // Creates a doubly linked list with easy node access for testing
		DNode head = list.get(0); // first node in the list
		DNode target = null;
		
		target = head.searchForwards(list.get(3), "a"); // searches for a from the third node
		assertNull(target); // does not find it there
		
		target = head.searchForwards(list.get(3), "e");
		assertSame(target, list.get(4));  // The two references should be the same
		assertSame(target.next, list.get(5)); // but does find "e"
		
		target = head.searchForwards(list.get(3), "f");
		assertSame(target, list.get(5));  
		assertSame(target.next, null);
		
		target = head.searchForwards(list.get(5), "f");
		assertSame(target, list.get(5));  
		assertSame(target.next, null);
		
		target = head.searchForwards(list.get(0), "f");
		assertSame(target, list.get(5));  
		assertSame(target.next, null);
		
		target = head.searchForwards(list.get(0), "d");
		assertSame(target, list.get(3));  
		assertSame(target.next, list.get(4));
		
		target = head.searchForwards(null, "d");
		assertNull(target);
	}

	@Test
	public void testSearchBackwards() {

		String[] items = {"a","b","c","d","e","f"};
		ArrayList<DNode> list = createList(items);

		DNode head = list.get(0);
		DNode curr = list.get(3);
		DNode target = null;

		target = head.searchBackwards(curr, "f");
		assertNull(target);	
		
		target = head.searchBackwards(curr, "a");
		assertSame(target, list.get(0));
		assertSame(target.prev,null);

		target = head.searchBackwards(curr, "b");
		assertSame(target, list.get(1));
		assertSame(target.prev,list.get(0));
		
		target = head.searchBackwards(null, "b");
		assertNull(target);
		
		target = head.searchBackwards(list.get(0), "a");
		assertSame(target, list.get(0));
		assertSame(target.prev,null);
		
		target = head.searchBackwards(list.get(0), "b");
		assertNull(target);
	}
	
	@Test
	public void testInsertAfter() {
		
		String[] items = {"a","b","c","d","e","f"};
		ArrayList<DNode> list = createList(items);

		String[] testArray = {"a","b","z","c","d","e","f"};
		
		DNode head = list.get(0);
		DNode curr = list.get(1);
		DNode newNode = new DNode("z");

		head.insertAfter(curr, newNode); // "z" should be inserted after "b"
		
		DNode temp = head;
		for (int i = 0; i < testArray.length-1; i++){
			assertEquals(testArray[i], temp.contents);
			temp = temp.next;
		}
		assertEquals(testArray[testArray.length-1], temp.contents);
		
		for (int i = testArray.length-1; i >= 0; i--){
			assertEquals(testArray[i], temp.contents);
			temp = temp.prev;
		}
		
		String[] items2 = {"a","b","c","d","e","f"};
		ArrayList<DNode> list2 = createList(items2);

		String[] testArray2 = {"a","b","c","d","e","f","z"};
		
		DNode head2 = list2.get(0);
		DNode curr2 = list2.get(5);
		DNode newNode2 = new DNode("z");
		
		head2.insertAfter(curr2, newNode2); // "z" should be inserted after "f"
		
		DNode temp2 = head2;
		for (int i = 0; i < testArray2.length-1; i++){
			assertEquals(testArray2[i], temp2.contents);
			temp2 = temp2.next;
		}
		assertEquals(testArray2[testArray2.length-1], temp2.contents);
		
		for (int i = testArray2.length-1; i >= 0; i--){
			assertEquals(testArray2[i], temp2.contents);
			temp2 = temp2.prev;
		}
		
		
		String[] items3 = {"a","b","c","d","e","f"};
		ArrayList<DNode> list3 = createList(items3);

		String[] testArray3 = {"a","z","b","c","d","e","f"};
		
		DNode head3 = list3.get(0);
		DNode curr3 = list3.get(0);
		DNode newNode3 = new DNode("z");
		
		head3.insertAfter(curr3, newNode3); // "z" should be inserted after "a"
		
		DNode temp3 = head3;
		for (int i = 0; i < testArray3.length-1; i++){
			assertEquals(testArray3[i], temp3.contents);
			temp3 = temp3.next;
		}
		assertEquals(testArray3[testArray3.length-1], temp3.contents);
		
		for (int i = testArray3.length-1; i >= 0; i--){
			assertEquals(testArray3[i], temp3.contents);
			temp3 = temp3.prev;
		}
	}
	
	@Test
	public void testInsertBefore() {
		
		String[] items = {"a","b","c","d","e","f"};
		ArrayList<DNode> list = createList(items);

		String[] testArray = {"a","z","b","c","d","e","f"};
		
		DNode head = list.get(0);
		DNode curr = list.get(1);
		DNode newNode = new DNode("z");
		
		head.insertBefore(curr, newNode); //Now insert "z" before "b"
		
		DNode temp = head;
		for (int i = 0; i < testArray.length-1; i++){
			assertEquals(testArray[i], temp.contents);
			temp = temp.next;
		}
		assertEquals(testArray[testArray.length-1], temp.contents);
		
		for (int i = testArray.length-1; i >= 0; i--){
			assertEquals(testArray[i], temp.contents);
			temp = temp.prev;
		}
		
		String[] items2 = {"a","b","c","d","e","f"};
		ArrayList<DNode> list2 = createList(items2);

		String[] testArray2 = {"a","b","c","d","e","z","f"};
		
		DNode head2 = list2.get(0);
		DNode curr2 = list2.get(5);
		DNode newNode2 = new DNode("z");
		
		head2.insertBefore(curr2, newNode2); // "z" should be inserted before "f"
		
		DNode temp2 = head2;
		for (int i = 0; i < testArray2.length-1; i++){
			assertEquals(testArray2[i], temp2.contents);
			temp2 = temp2.next;
		}
		assertEquals(testArray2[testArray2.length-1], temp2.contents);
		
		for (int i = testArray2.length-1; i >= 0; i--){
			assertEquals(testArray2[i], temp2.contents);
			temp2 = temp2.prev;
		}
		
		
		String[] items3 = {"a","b","c","d","e","f"};
		ArrayList<DNode> list3 = createList(items3);

		String[] testArray3 = {"z","a","b","c","d","e","f"};
		
		DNode head3 = list3.get(0);
		DNode curr3 = head3;
		DNode newNode3 = new DNode("z");
		
		head3.insertBefore(curr3, newNode3); // "z" should be inserted before "a"
		
		if(curr3 == head3) {
			head3 = head3.prev;
		}
		
		DNode temp3 = head3;
		for (int i = 0; i < testArray3.length-1; i++){
			assertEquals(testArray3[i], temp3.contents);
			temp3 = temp3.next;
		}
		assertEquals(testArray3[testArray3.length-1], temp3.contents);
		
		for (int i = testArray3.length-1; i >= 0; i--){
			assertEquals(testArray3[i], temp3.contents);
			temp3 = temp3.prev;
		}
	}
	
	// The following tests concern the class DLSList
	
	@Test
	public void testAddNewNode() {
		String[] items = {"b","c","e"};
		ArrayList<DNode> list = createList(items);
		DNode head = list.get(0);

		DLSList testList= new DLSList();
		testList.head= head;
		testList.numNodes= items.length;
		testList.lastVisited= testList.head; // Initialise testList to satisfy the class invariant
		
		// now prepare and add a node
		DNode nodeToAdd= new DNode("d"); 
		testList.addNewNode(nodeToAdd);
		
		String testArray1[]= {"b", "c", "d", "e" }; // this is what the list should look like after "d" is added in correct sorted order

		DNode temp= testList.head;
		for(int i=0; i< testArray1.length; i++){ // Checks the order of nodes starting from the first node
			assertEquals(testArray1[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray1.length, testList.numNodes);		
		
		nodeToAdd= new DNode("f"); 
		testList.addNewNode(nodeToAdd);

		String testArray2[]= {"b", "c", "d", "e", "f" };
		temp = testList.head;
		for(int i=0; i< testArray2.length; i++){ 
			assertEquals(testArray2[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray2.length, testList.numNodes);		
	
		nodeToAdd= new DNode("a"); 
		testList.addNewNode(nodeToAdd);

		String testArray3[]= {"a", "b", "c", "d", "e", "f" };
		temp = testList.head;
		for(int i=0; i< testArray3.length; i++){ 
			assertEquals(testArray3[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray3.length, testList.numNodes);	
		
		nodeToAdd= new DNode("z"); 
		testList.addNewNode(nodeToAdd);

		String testArray4[]= {"a", "b", "c", "d", "e", "f", "z"};
		temp = testList.head;
		for(int i=0; i< testArray4.length; i++){ 
			assertEquals(testArray4[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray4.length, testList.numNodes);	
		
		nodeToAdd= new DNode("c"); 
		testList.addNewNode(nodeToAdd);
		
		String testArray5[]= {"a", "b", "c", "c", "d", "e", "f", "z"};
		temp = testList.head;
		for(int i=0; i< testArray5.length; i++){ 
			assertEquals(testArray5[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray5.length, testList.numNodes);	
		
		String[] items2 = {"B","C","E"};
		ArrayList<DNode> list2 = createList(items2);
		DNode head2 = list2.get(0);

		DLSList testList2= new DLSList();
		testList2.head= head2;
		testList2.numNodes= items2.length;
		testList2.lastVisited= testList2.head;
		
		nodeToAdd= new DNode("Bat"); 
		testList2.addNewNode(nodeToAdd);
		
		String testArray6[]= {"B", "Bat", "C", "E"};
		temp = testList2.head;
		for(int i=0; i< testArray6.length; i++){ 
			assertEquals(testArray6[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray6.length, testList2.numNodes);	
		
	}
	
	@Test
	public void testRemoveNode() {
		String[] items = {"a","b","c","d"};
		ArrayList<DNode> list = createList(items);
		DNode head = list.get(0);

		DLSList testList= new DLSList();
		testList.head= head;
		testList.numNodes= items.length;
		testList.lastVisited= testList.head; // Initialise testList to satisfy the class invariant
		
		// now remove a node
		testList.removeNode("a");
		
		String testArray1[]= {"b", "c", "d"}; // This is what the list should look like after the removal of "a"

		DNode temp= testList.head;
		for(int i=0; i< testArray1.length; i++){ // Checks the order of nodes starting from the first node
			assertEquals(testArray1[i], temp.contents);
			temp=temp.next;
		}
		assertEquals(testArray1.length, testList.numNodes);	
		
		String[] items2 = {"a","b","a","d"};
		ArrayList<DNode> list2 = createList(items2);
		DNode head2 = list2.get(0);

		DLSList testList2 = new DLSList();
		testList2.head = head2;
		testList2.numNodes= items2.length;
		testList2.lastVisited= testList2.head; // Initialise testList to satisfy the class invariant
		
		// now remove a node
		testList2.removeNode("a");
		
		String testArray2[]= {"b", "d"}; // This is what the list should look like after the removal of "a"

		DNode temp2 = testList2.head;
		for(int i=0; i< testArray2.length; i++){ // Checks the order of nodes starting from the first node
			assertEquals(testArray2[i], temp2.contents);
			temp2=temp2.next;
		}
		assertEquals(testArray2.length, testList2.numNodes);
		
		String[] items3 = {"a", "b", "a", "a"};
		ArrayList<DNode> list3 = createList(items3);
		DNode head3 = list3.get(0);

		DLSList testList3 = new DLSList();
		testList3.head = head3;
		testList3.numNodes= items3.length;
		testList3.lastVisited= testList3.head; // Initialise testList to satisfy the class invariant
		
		// now remove a node
		testList3.removeNode("a");
		
		String testArray3[]= {"b"}; // This is what the list should look like after the removal of "a"

		DNode temp3 = testList3.head;
		for(int i=0; i< testArray3.length; i++){ // Checks the order of nodes starting from the first node
			assertEquals(testArray3[i], temp3.contents);
			temp3=temp3.next;
		}
		assertEquals(testArray3.length, testList3.numNodes);
		
		String[] items4 = {"a", "b", "a", "d"};
		ArrayList<DNode> list4 = createList(items4);
		DNode head4 = list4.get(0);

		DLSList testList4 = new DLSList();
		testList4.head = head4;
		testList4.numNodes= items4.length;
		testList4.lastVisited= testList4.head; // Initialise testList to satisfy the class invariant
		
		// now remove a node
		testList4.removeNode("d");
		
		String testArray4[]= {"a", "b", "a"}; // This is what the list should look like after the removal of "a"

		DNode temp4 = testList4.head;
		for(int i=0; i< testArray4.length; i++){ // Checks the order of nodes starting from the first node
			assertEquals(testArray4[i], temp4.contents);
			temp4=temp4.next;
		}
		assertEquals(testArray4.length, testList4.numNodes);
		
	}
	
	@Test
	public void testVisit() {

		String[] items = {"a","b","c","d"};
		ArrayList<DNode> list = createList(items);
		DNode head = list.get(0);

		DLSList testList= new DLSList();
		testList.head= head;
		testList.numNodes= items.length;
		testList.lastVisited= testList.head; // Initialise testList to satisfy the class invariant
		
		DNode temp= testList.visit("b");
		DNode curr= list.get(1);
		
		
		assertSame(curr, temp);	
		assertSame(curr, testList.lastVisited);	// check that lastVisited has been updated
		
		DNode temp69= testList.visit("d");
		DNode curr69= list.get(3);
		
		assertSame(curr69,temp69);
		assertSame(curr69,testList.lastVisited);
		
		
		String[] items2 = {"a","b","c","d"};
		ArrayList<DNode> list2 = createList(items2);
		DNode head2 = list2.get(0);

		DLSList testList2= new DLSList();
		testList2.head= head2;
		testList2.numNodes= items2.length;
		testList2.lastVisited= testList2.head; // Initialise testList to satisfy the class invariant
		
		DNode temp2 = testList2.visit("d");
		DNode curr2 = list2.get(3);
		
		
		assertSame(curr2, temp2);	
		assertSame(curr2, testList2.lastVisited); // check that lastVisited has been updated
		
		String[] items3 = {"a","b","c","d"};
		ArrayList<DNode> list3 = createList(items3);
		DNode head3 = list3.get(0);

		DLSList testList3= new DLSList();
		testList3.head= head3;
		testList3.numNodes= items3.length;
		testList3.lastVisited= testList3.head; // Initialise testList to satisfy the class invariant
		
		DNode temp3 = testList3.visit("f");
		
		assertNull(temp3);		
	}
	
	
	// The following tests concern the class SimpleBlockchain
	
	@Test
	public void testAddBlock() {
		SimpleBlockchain testBc= new SimpleBlockchain("hello");
		
		BlockchainNode newBc= new BlockchainNode ("number two", testBc.genesisNode.currHash, 1);
		
		testBc.addBlock("number two");
		
		assertEquals("number two", testBc.lastNode.contents);
		assertEquals(1, testBc.lastNode.blockNumber);
		assertEquals(newBc.currHash, testBc.lastNode.currHash);
		assertSame(testBc.genesisNode.next, testBc.lastNode);		
		
		BlockchainNode newBc2 = new BlockchainNode ("number three", newBc.currHash, 2);
		
		testBc.addBlock("number three");
		
		assertEquals("number three", testBc.lastNode.contents);
		assertEquals(2, testBc.lastNode.blockNumber);
		assertEquals(newBc2.currHash, testBc.lastNode.currHash);
		assertSame(testBc.genesisNode.next.next, testBc.lastNode);
		
		BlockchainNode newBc3 = new BlockchainNode ("c", newBc2.currHash, 3);
		
		testBc.addBlock("c");
		
		assertEquals("c", testBc.lastNode.contents);
		assertEquals(3, testBc.lastNode.blockNumber);
		assertEquals(newBc3.currHash, testBc.lastNode.currHash);
		assertSame(testBc.genesisNode.next.next.next, testBc.lastNode);
	}
	
	
	@Test
	public void testValidate() {
		SimpleBlockchain testBc= new SimpleBlockchain("hello");
				
		testBc.addBlock("number two"); // assumes addBlock is correctly implemented
		testBc.addBlock("number three");
		testBc.addBlock("number four");
		testBc.addBlock("number five");
		
		assertTrue(testBc.validate());	
		
		testBc.genesisNode.contents= "something else"; // A simple attack: Change the block chain contents
		
		assertFalse(testBc.validate()); // No longer valid
	}
	
	
	@Test
	public void findTamperedNode() {
		SimpleBlockchain testBc= new SimpleBlockchain("hello");
				
		testBc.addBlock("number two"); // assumes addBlock is correctly implemented
		
		testBc.lastNode.contents= "something else"; // A simple attack: Change the block chain contents
		
		assertFalse(testBc.validate()); // No longer valid
		assertSame(testBc.lastNode, testBc.findTamperedNode()); // Last node has been altered	
		
		SimpleBlockchain testBc2= new SimpleBlockchain("hello");
		
		testBc2.addBlock("number two"); // assumes addBlock is correctly implemented
		
		testBc2.genesisNode.contents= "something else"; // A simple attack: Change the block chain contents
		
		assertFalse(testBc2.validate()); // No longer valid
		assertSame(testBc2.genesisNode, testBc2.findTamperedNode()); // Last node has been altered	
		
		SimpleBlockchain testBc3= new SimpleBlockchain("hello");
		
		testBc3.addBlock("number two"); // assumes addBlock is correctly implemented
		testBc3.addBlock("number three");
		testBc3.addBlock("number four");
		testBc3.addBlock("number five");
		
		testBc3.genesisNode.next.next.contents = "something else"; // A simple attack: Change the block chain contents
		testBc3.lastNode.contents = "changed";
		
		assertFalse(testBc3.validate()); // No longer valid
		assertSame(testBc3.genesisNode.next.next, testBc3.findTamperedNode()); // Last node has been altered	
				
	}
	
	public ArrayList<DNode> createList(String[] items){
		ArrayList<DNode> list = new ArrayList<>();
		if (items.length > 0){
			list.add(new DNode(items[0],null,null));
			for (int i = 1; i < items.length; i++){
				DNode toAdd = new DNode(items[i],list.get(i-1),null);
				list.add(toAdd);
				list.get(i-1).next = toAdd;
			}
		}
		return list;
	}
	
}
