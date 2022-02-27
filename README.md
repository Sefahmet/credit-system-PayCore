# credit-system-PayCore
**Credit System** 

*This project was created for the PayCore Java Spring Bootcamp.*

The customer's identity number, name-surname, monthly income and phone information are obtained, and the credit score service is sent to the credit score service, which is assumed to have been written with the identity number before, and the credit limit is shown to the user according to the following rules by obtaining the credit score of the relevant person. At the same time, the customer should be able to call a previous Credit Limit application again. Thus;

Credit Limit Service has 2 main functions.

The first is to return a Credit Limit by calculating the Credit Score of the input Customer Object.

The second is to return the Credit Limit corresponding to the given national identification number.

**Classes:** 

![CreditSystem drawio](https://user-images.githubusercontent.com/58665552/155879223-1e212bd9-4595-4615-a0c9-9a187bfe1e4d.png)


**DataBase of Credit Score**

![Ekran Resmi 2022-02-27 13 32 09](https://user-images.githubusercontent.com/58665552/155879655-b876c263-74b5-4730-a739-7f286180dbec.png)

<ol> 
<li>If Credit Score < 500, return Rejected and Http Status Not Acceptable</li>
  
![Ekran Resmi 2022-02-27 13 28 36](https://user-images.githubusercontent.com/58665552/155879234-bd0bdf28-84ba-4da4-9181-8eb0144c9052.png)

<li>If Credit Score [500,1000) and Monthly Income < 5000, return Credit Score (10000) and Http Status OK.</li>
  
![Ekran Resmi 2022-02-27 13 29 49](https://user-images.githubusercontent.com/58665552/155879248-a70f7daf-22f8-4646-9e51-149527d6a5bf.png)

<li>If Credit Score [500,1000) and Monthly Income >= 5000, return Credit Score (20000) and Http Status OK.</li>

![Ekran Resmi 2022-02-27 13 29 37](https://user-images.githubusercontent.com/58665552/155879461-27d5b97a-7695-4b32-b047-41a698eeb6c8.png)

<li>If Credit Score >=1000, return Credit Score (Monthly Income \* 4) and Http Status OK.</li>
  
![Ekran Resmi 2022-02-27 13 29 57](https://user-images.githubusercontent.com/58665552/155879249-5e74000c-7b97-4d6c-aebb-dfd077c4997d.png)

<li>If Credit Score Not Found, return Credit Score Not Found and Http Status Not Found.</li>

![Ekran Resmi 2022-02-27 13 30 03](https://user-images.githubusercontent.com/58665552/155879251-39c4a41b-8f1e-433b-b968-8c862454e4ed.png)

</ol>

**If credit limit has done before, it accessible with National Identification Number.**

<ol>

<li>The Successful test.</li>

![Ekran Resmi 2022-02-27 13 30 25](https://user-images.githubusercontent.com/58665552/155879259-d3e8b53b-c5f2-4933-b895-4dbe5dab4dab.png)

<li>The Not found application. </li>

![Ekran Resmi 2022-02-27 13 31 17](https://user-images.githubusercontent.com/58665552/155879514-3cc6500d-f088-45c7-8fab-cb69919dcf81.png)

</ol>

**The unit test Results**

![Ekran Resmi 2022-02-27 14 18 37](https://user-images.githubusercontent.com/58665552/155880312-2c80f186-d4e4-42de-abda-129e7a319e32.png)

