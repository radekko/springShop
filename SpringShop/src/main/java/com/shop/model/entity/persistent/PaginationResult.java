package com.shop.model.entity.persistent;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
 
public class PaginationResult<E> {
 
   private int totalRecords;
   private int currentPage;
   private List<E> list;
   private int maxResult;
   private int totalPages;
 
   private int maxNavigationPage;
 
   private List<Integer> navigationPages;
   
   @Autowired
   private SessionFactory sessionFactory;

   protected Session getSession(){
       return sessionFactory.getCurrentSession();
   }
 
   // @page: 1, 2, ..
   public <T> PaginationResult(Class<T> entity, int page, int maxResult, int maxNavigationPage) {
  
	   Criteria criteria = getSession().createCriteria(entity);
	   criteria.setFirstResult(0);
	   criteria.setMaxResults(maxResult);
	   List<T> firstPage = criteria.list();
	   
	   Criteria criteriaCount = getSession().createCriteria(entity);
	   criteriaCount.setProjection(Projections.rowCount());
	   Integer count = (Integer) criteriaCount.uniqueResult();
	   
       // Total records.
       this.totalRecords = count;
//       this.currentPage = pageIndex + 1;
       this.list = (List<E>) firstPage;
       this.maxResult = maxResult;
 
       this.totalPages = (this.totalRecords / this.maxResult) + 1;
       this.maxNavigationPage = maxNavigationPage;
 
       if (maxNavigationPage < totalPages) {
           this.maxNavigationPage = maxNavigationPage;
       }
 
       this.calcNavigationPages();
   }
 
   private void calcNavigationPages() {
 
       this.navigationPages = new ArrayList<Integer>();
 
       int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
 
       int begin = current - this.maxNavigationPage / 2;
       int end = current + this.maxNavigationPage / 2;
 
       // First page
       navigationPages.add(1);
       if (begin > 2) {
           // For '...'
           navigationPages.add(-1);
       }
 
       for (int i = begin; i < end; i++) {
           if (i > 1 && i < this.totalPages) {
               navigationPages.add(i);
           }
       }
 
       if (end < this.totalPages - 2) {
           // For '...'
           navigationPages.add(-1);
       }
       // Last page.
       navigationPages.add(this.totalPages);
   }
 
   public int getTotalPages() {
       return totalPages;
   }
 
   public int getTotalRecords() {
       return totalRecords;
   }
 
   public int getCurrentPage() {
       return currentPage;
   }
 
   public List<E> getList() {
       return list;
   }
 
   public int getMaxResult() {
       return maxResult;
   }
 
   public List<Integer> getNavigationPages() {
       return navigationPages;
   }
  
}