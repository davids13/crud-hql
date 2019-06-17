# crud-hql
Hibernate Query Language (HQL) is same as SQL (Structured Query Language) but it doesn't depends on the table of the database. 
Instead of table name, we use class name in HQL. So it is database independent query language.

Ex getAllModels:
//get the current hibernate session
Session currentSession = entityManager.unwrap(Session.class);
Query query = currentSession.createQuery("SELECT model FROM Model model");
List modelList = queryGetResultList();
return modelList;
