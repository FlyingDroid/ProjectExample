package com.weidukeji.agriculture.widget.pinyinparser;

import com.weidukeji.agriculture.entity.LocalSortInfo;

import java.util.Comparator;

/**
 * 
 * @author Mr.Z
 */
public class PinyinComparator implements Comparator<LocalSortInfo> {

	public int compare(LocalSortInfo o1, LocalSortInfo o2) {
		if(o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
			return -1;
		} else if(o1.getSortLetters().equals("#") || o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
