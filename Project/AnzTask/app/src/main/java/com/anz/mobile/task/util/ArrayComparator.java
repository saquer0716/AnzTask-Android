/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * Comparator classes for all different kind of data structure. Just follow existing method for
 * adding new comparator like date comparator.
 *
 * If multiple condition comparator is required (like sort magnitude first, then sort depth in
 * those equal magnitude items), just add another compare logic before returning result
 */


package com.anz.mobile.task.util;

import com.anz.mobile.anzvolley.wrapper.model.Earthquake;

import java.util.Comparator;

public class ArrayComparator {
	private static class MagnitudeComparator implements Comparator<Earthquake>{
        private boolean isAscending;
        MagnitudeComparator(boolean ascending) {
            isAscending = ascending;
		}

		public int compare(Earthquake lhs, Earthquake rhs) {
            float depthA = Float.valueOf(lhs.getMagnitude());
            float depthB = Float.valueOf(rhs.getMagnitude());
            if (depthA < depthB) {
                return  isAscending ? -1 : 1;
            } else if (depthA > depthB) {
                return isAscending ? 1 : -1;
            } else {
                return 0;
            }
        }
	}

    private static class DepthComparator implements Comparator<Earthquake>{
        private boolean isAscending;
        DepthComparator(boolean ascending) {
            isAscending = ascending;
        }

        public int compare(Earthquake lhs, Earthquake rhs) {
            float depthA = Float.valueOf(lhs.getDepth());
            float depthB = Float.valueOf(rhs.getDepth());
            if (depthA < depthB) {
                return  isAscending ? -1 : 1;
            } else if (depthA > depthB) {
                return isAscending ? 1 : -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * return a comparator for comparing earthquake magnitude
     *
     * @param  ascending whether the sort should be in ascending order
     */
	public static MagnitudeComparator magnitudeComparator(boolean ascending){
		return new MagnitudeComparator(ascending);
	}

    /**
     * return a comparator for comparing earthquake depth
     *
     * @param  ascending whether the sort should be in ascending order
     */
    public static DepthComparator depthComparator(boolean ascending){
        return new DepthComparator(ascending);
    }
}
