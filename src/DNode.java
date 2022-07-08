import java.security.MessageDigest;

class DNode {
// Node for building a Doubly-linked list
	String contents;
	DNode next;
	DNode prev;
	
	DNode (String k) {  // Constructor: builds a node which can be searched forwards and backwards
		next= null; prev= null;
		contents= k;		
	}
	
	DNode (String k, DNode Prev, DNode Next){ // Constructor: builds a node with given references
		this.prev = Prev;
		this.next = Next;
		this.contents = k;
	}
	
	DNode searchForwards(DNode curr, String key) { //TODO
		// Pre: curr is a reference to a node in a doubly-linked list. 
		// Post: returns the reference of the node whose contents match key if it occurs in the portion of the list
		// beginning at curr and ending at the last node. Returns null if key does not occur in that portion.
		if(curr == null || curr.contents !=key && curr.next == null) { //curr is null and curr is the last node in the list.
			return null;
		}
		
		DNode temp = curr;
		for( ; temp != null && temp.contents!=key; temp = temp.next) {
		}
		
		return temp;	
	}
	
	DNode searchBackwards(DNode curr, String key) { //TODO
		// Pre: curr is a reference to a doubly-linked list. 
		// Post: returns the reference of the node whose contents match key if it occurs in the portion of the list
		// beginning at the head and ending at curr. Returns null if key does not occur in that portion.
		if(curr == null || curr.contents != key && curr.prev == null) {
			return null;
		}
		
		DNode temp = curr;
		for( ; temp != null && temp.contents != key; temp = temp.prev) {
		}
		
		return temp;	
	}
	
	void insertAfter(DNode curr, DNode newNode) { //TODO
		// Pre: curr is a reference to a node in the list and newNode is a  reference to a node to be inserted.
		// Post: newNode is inserted between curr and its next neighbour in the list, i.e.
		// let N be curr's next neighbour, then after the insert: curr.next points to newNode, newNode.next points to N
		// N.prev points to newNode and newNode.prev points to curr.
		// If curr has no next neighbour, then newNode is inserted as the last node after curr.
		if(curr == null) {
			curr = newNode;
		}
		
		else if(curr.next == null) {
			curr.next = newNode;
			newNode.prev = curr;
		}
		
		else {
			DNode N = curr.next;
			curr.next = newNode;
			newNode.prev = curr;
			newNode.next = N;
			N.prev = newNode;
		}
	}

	void insertBefore(DNode curr, DNode newNode) { //TODO
		// Pre: curr is a reference to a node in the list and newNode is a reference to a node to be inserted.
		// Post: newNode is inserted between curr and its previous neighbour, i.e.
		// let P be curr's previous neighbour, then after the insert: P.next points to newNode, newNode.next points to curr
		// curr.prev points to newNode and newNode.prev points to P.
		// If curr has no previous neighbour, then newNode is inserted as the first node before curr.	
		if(curr == null) {
			curr = newNode;
		}
		
		else if(curr.prev == null) {
			newNode.next = curr;
			curr.prev = newNode;
			newNode.prev = null;
		}
		
		else {
			DNode P = curr.prev;
			curr.prev = newNode;
			newNode.next = curr;
			newNode.prev = P;
			P.next = newNode;
		}
	}
}

class DLSList {
	// Class invariant: The nodes in the list are sorted (ascending) according to the contents
	// AND numNodes == the number of nodes in the list
	// AND (lastVisited points to the node which was the last valid access after method visit is called
	//     OR is set to head (in case removeNode demands it) or it is initialised)
	DNode head;  // The first node in the list
	DNode lastVisited; // The address of the node last visited
	int numNodes; // The number of nodes in the list
	
	DLSList (){
		head= null;
		lastVisited= null;
		numNodes= 0;
	}
	
	void addNewNode(DNode newNode) { //TODO
		// Post: newNode is inserted into the current list in correct sorted order
		// numNodes is adjusted to be equal to the number of nodes in the list
		if(head == null) {
			head = newNode;
		}
		
		if(head.prev == null && head.next == null) {
			if(newNode.contents.compareTo(head.contents) == -1) {
				head.prev = newNode;
				newNode.next = head;
			}
			
			else {
				head.next = newNode;
				newNode.prev = head;
			}
		}
		
		DNode temp = head;
		for( ; temp.next != null && newNode.contents.compareTo(temp.contents) != -1; temp = temp.next){
		}
		
		if(temp.next == null && newNode.contents.compareTo(temp.contents) != -1) {
			temp.next = newNode;
			newNode.prev = temp;
		}
		
		else if(temp.prev == null && newNode.contents.compareTo(temp.contents) == -1) {
			newNode.next = temp;
			temp.prev = newNode;
			head = newNode;
		}
		
		else {
			DNode P = temp.prev;
			temp.prev = newNode;
			newNode.next = temp;
			P.next = newNode;
			newNode.prev = P;
		}
		numNodes++;
	}
	
	void removeNode(String key) { //TODO
		// Post: All occurrences of nodes with contents field equal to key are removed.
		// If lastVisited points to one of the removed nodes, then lastVisited is set to head
		// numNodes is adjusted to be equal to the number of nodes in the list
		if(head.prev == null && head.contents == key && head.next == null) {
			head = null;
		}
		
		else if(head.prev == null && head.contents == key) {
			head = head.next;
			head.prev = null;
		}
		
		DNode temp = head;
		while(temp != null) {
			for(; temp!=null && temp.contents != key; temp = temp.next) {
		}
			if(temp != null && temp.next !=null && temp.contents == key) {
				temp.prev.next =temp.next;
				temp.next.prev = temp.prev;
				if(lastVisited == temp) {
					lastVisited = head;
				}
				temp = temp.next;
			}
			
			else if(temp != null && temp.next == null && temp.contents == key) {
				temp.prev.next = null;
				temp.prev = null;
				if(lastVisited == temp) {
					lastVisited = head;
				}
				temp = temp.next;
			}
		}
		
		temp = head;
		int count = 0;
		for(; temp!=null;temp = temp.next) {
			count = count + 1;
		}
		
		numNodes = count;
	}
	
	DNode visit(String key) { //TODO
		// Post: Returns the address of the first node (in ascending order) whose contents equal key, and null if there is no such node;
		// lastVisited is set to the address returned if it is not null, otherwise lastVisited remains unchanged
		if(head == null || head.contents != key && head.next == null) {
			return null;
		}
		
		else if(head.contents == key && head.next == null) {
			lastVisited = head;
			return head;
		}
		
		DNode temp = head;
		for( ; temp != null && temp.contents != key; temp = temp.next) {
		}
		
		if(temp == null) {
			return null;
		}
		
		else {
			lastVisited = temp;
			return temp;
		}
	}
}

class BlockchainNode { // The basic BlockchainNode...
	String currHash;  // To store the hash
	String contents;  // To store the data
	int blockNumber;  // The number of the node in the block after the Genesis Node
	BlockchainNode next; // The next BlockchainNode
	BlockchainNode prev; // The previous BlockchainNode
	
	BlockchainNode (String s) {
		// Creates a Genesis node
		currHash= StringUtil.applySha256(Integer.toString(0)+"AllZeros:This is the Genesis String!"+s);
		contents= s;
		blockNumber= 0;
		next= null;
		prev= null;
	}	
	
	BlockchainNode (String s, String pH, int bN) {
	// Creates a new block numbered bN with contents s	
		currHash= StringUtil.applySha256(Integer.toString(bN)+pH+s);
		contents= s;
		blockNumber= bN;
		next= null;	
		prev= null;
	}
}

class SimpleBlockchain {
	// Class invariant: all nodes in the Blockchain satisfy "Blockchain Validity"
	// i.e.  Blockchain Validity holds of all nodes (except the Genesis node):
	// StringUtil.applySha256(this.blockNumber+ prev.currHash+this.contents) == this.currHash
	// AND the Genesis node has been correctly initialised
	BlockchainNode genesisNode; // Created by calling BlockchainNode (String s)
	BlockchainNode lastNode;   // The last node in the Blockchain

		SimpleBlockchain(String s){
			genesisNode= new BlockchainNode(s);
			lastNode= genesisNode;
		}
		
	
	void addBlock(String s) { //TODO
		// Post: Creates a new valid block with contents s, and adds it
		// to the current SimpleBlockchain so that the result satisfies the blockchain validity condition.
		if(this.genesisNode == null && this.lastNode == null) {
			BlockchainNode genesis = new BlockchainNode("number one");
			this.genesisNode = genesis;
			this.lastNode = genesis;
		}
		
		BlockchainNode newNode = new BlockchainNode(s,this.lastNode.currHash,this.lastNode.blockNumber+1);
		
		this.lastNode.next = newNode;
		newNode.prev = this.lastNode;
		lastNode = lastNode.next;
	}
	
	boolean validate () { //TODO
		// Post: Returns true if the SimpleBlockchain is valid, i.e. satisfies the blockChain validity condition
		// null SimplBlockchains are valid
		if(this.genesisNode == null && this.lastNode == null) {
			return true;
		}
		
		if(!this.genesisNode.currHash.equals(StringUtil.applySha256(Integer.toString(0)+"AllZeros:This is the Genesis String!"+this.genesisNode.contents))){
			return false;
		}
		
		else if(this.genesisNode.currHash.equals(StringUtil.applySha256(Integer.toString(0)+"AllZeros:This is the Genesis String!"+this.genesisNode.contents)) && this.genesisNode.next == null){
			return true;
		}
		
		BlockchainNode temp = this.genesisNode.next;
		
		while(temp != null) {
			if(!temp.currHash.equals(StringUtil.applySha256(temp.blockNumber+temp.prev.currHash+temp.contents))){
				return false;
			}
			temp = temp.next;
		}
		
		return true;
	}	
	
	BlockchainNode findTamperedNode() { //TODO
		// Post: If the blockchain does not satisfy the validity condition, 
		// returns the address of the first node which fails to validate
		// otherwise returns null
		if(this.genesisNode == null && this.lastNode == null) {
			return null;
		}
		
		if(!this.genesisNode.currHash.equals(StringUtil.applySha256(Integer.toString(0)+"AllZeros:This is the Genesis String!"+this.genesisNode.contents))){
			return this.genesisNode;
		}
		
		else if(this.genesisNode.currHash.equals(StringUtil.applySha256(Integer.toString(0)+"AllZeros:This is the Genesis String!"+this.genesisNode.contents)) && this.genesisNode.next == null){
			return null;
		}
		
		BlockchainNode temp = this.genesisNode.next;
		
		while(temp != null) {
			if(!temp.currHash.equals(StringUtil.applySha256(temp.blockNumber+temp.prev.currHash+temp.contents))){
				return temp;
			}
			temp = temp.next;
		}
		
		return null;
	}	
}

/// This is what we will use for the hash function in making a blockchain
/// Do not change or remove this code
class StringUtil {
	//Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input){		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
}

