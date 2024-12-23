package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Storage {
	public SwapArea swapArea;
	public FileArea fileArea;
	public ArrayList<Integer> nodeSize;
	public ArrayList<ArrayList<Node>> freeNodes;
	
	public Storage(int swapAreaSize,int fileAreaSize) {
		swapArea=new SwapArea(0,swapAreaSize);
		nodeSize=new ArrayList<Integer>();
		int idx=0;
		for(int i=1;i<=swapAreaSize;i<<=1) {
			nodeSize.add(i);
			idx++;
		}
		freeNodes=IntStream.range(0, idx)
				.mapToObj(i -> new ArrayList<Node>())
				.collect(Collectors.toCollection(ArrayList::new));
		freeNodes.get(idx-1).add(new Node(-1,swapAreaSize,0));
		
		fileArea=new FileArea(fileAreaSize);
	}
	
	public Integer alloc(int processId, int allocSize) {
		int idx=Collections.binarySearch(nodeSize, allocSize);
		ArrayList<Node> list=freeNodes.get(idx);
		if(!list.isEmpty()) {
			for(Node node:list) {
				if(node.processId!=-1) {
					continue;
				}
				node.processId=processId;
				return node.startAddress;
			}
		}
		for(int i=idx+1;i<freeNodes.size();i++) {
			list=freeNodes.get(i);
			if(list.isEmpty()) {
				continue;
			}
			Integer k=Integer.valueOf(i);
			Integer minSize=Integer.valueOf(nodeSize.get(k));
			while(minSize.compareTo(nodeSize.get(idx))>0) {
				list=freeNodes.get(k);
				for(Node node:list) {
					if(node.processId!=-1) {
						continue;
					}
					int xNodeSize=node.size>>1;
					Node x1=new Node(-1, xNodeSize, node.startAddress);
					Node x2=new Node(-1, xNodeSize, node.startAddress+xNodeSize);
					list.remove(node);
					freeNodes.get(k-1).add(x1);
					freeNodes.get(k-1).add(x2);
					break;
				}
				k--;
				minSize>>=1;
			}
			list=freeNodes.get(k);
			for(Node node:list) {
				if(node.processId!=-1) {
					continue;
				}
				node.processId=processId;
				return node.startAddress;
			}
		}
		return null;
	}
	
	public void recycle(int address) {
		ArrayList<Node> list=null;
		Node node=null;
		for(int i=0;i<freeNodes.size();i++) {
			list=freeNodes.get(i);
			if(list.isEmpty()) {
				continue;
			}
			for(int j=0;j<list.size();j++) {
				node=list.get(j);
				if(node.startAddress==address) {
					node.processId=-1;
				}
			}
			ArrayList<Node> removeList=new ArrayList<Node>();
			boolean allFree=false;
			Integer nodeSizeK=null;
			Integer buddyAddress=null;
			for(int j=0;j<list.size();j++) {
				node=list.get(j);
				if(node.processId!=-1) {
					continue;
				}
				removeList.add(node);
				nodeSizeK=Integer.valueOf(node.size);
				buddyAddress=Integer.valueOf(isPowerOfTwo(address)?address+nodeSizeK:address-nodeSizeK);
				for(int k=0;k<list.size();k++) {
					node=list.get(k);
					if(node.startAddress!=buddyAddress || node.processId!=-1) {
						continue;
					}
					allFree=true;
					removeList.add(node);
					break;
				}
			}
			if(!allFree) {
				continue;
			}
			for(Node x:removeList) {
				list.remove(x);
			}
			freeNodes.get(i+1).add(new Node(-1, nodeSizeK<<1, isPowerOfTwo(address)?address:buddyAddress));
		}
	}
	
	private boolean isPowerOfTwo(int x) {
		return x>0 && (x&(x-1))==0;
	}
}
