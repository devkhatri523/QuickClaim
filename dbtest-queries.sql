1) Find total claimed_charge of the exported documents
  select a.insured_name as document_name, sum(a.claimed_charge) as total_claimed_charge   from document as a join batch as b
  on a.batch_id = b.id  where a.status='EXPORTED'
   group by a.id,a.insured_name

OR

select sum(claimed_charge) as total_claimed_charged from document
where status='EXPORTED'

2) Find insured_name, insured_address and claimed_charge for the documents that have status "TO_REPRICE" and customer id 1 and 2.

select a.insured_name as insured_name, a.insured_address as insured_address
,a.claimed_charge as claimed_charged
from document a,batch b  where a.status='TO_REPRICE' and
b.customer_id IN(1,2) group by a.insured_name