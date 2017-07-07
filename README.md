Steps for deployment this project:

1. Download or clone the repository to desctop
2. Open in IntelliJ IDEA as Maven Project

Steps for testing:

1. Open the CMD and move to dirrectory with project
2. Enter in CMD "mvn test" without cuotes

The requests for testing the project:

1. http://locahost:8080/products - for overview all products in warehouse.
2. http://locahost:8080/products/{productId} - for overview the information
about the product, which you set as {productId}. For example, the request of the form
"http://localhost:8080/products/1" will return a response of the form:
{
    "id": 5,
    "type": {
        "id": 3,
        "title": "Стиральная машина"
    },
    "brand": {
        "id": 3,
        "title": "Bosch"
    },
    "title": "WLG 20060",
    "count": 19,
    "price": 15005,
    "productFeatures": [
        {
            "id": 10,
            "feature": {
                "id": 6,
                "title": "Тип загрузки одежды"
            },
            "value": "Фронтальная загрузка"
        },
        {
            "id": 9,
            "feature": {
                "id": 5,
                "title": "Габариты"
            },
            "value": "60x50x40"
        }
    ]
}.
3. http://locahost:8080/products/brands - for overview all brands of products.
4. http://locahost:8080/products/types - for overview all types of products.
5. http://locahost:8080/products/{productId}/title - for look the title of the product
with id which you set as {productId}.
6. http://locahost:8080/products/{productId}/brand - for look the brand of the product
with id which you set as {brandId}.
7. http://locahost:8080/products/{productId}/brand?brandId={brandId} - for change the brand to brand
with id which you set as {brandId} of product with id equals {productId}.
8. http://locahost:8080/products/{productId}/type - for look the type of the product
with id which you set as {typeId}.
9. http://locahost:8080/products/{productId}/type?typeId={typeId} - for change the type to type
with id which you set as {typeId} of product with id equals {productId}.
10. http://locahost:8080/products/{productId}/features - for look the features of the product
with id which you set as {featuresId}.
11. http://locahost:8080/products/{productId}/features/{featureId} - for look the concrete feature of the product
with id which you set as {featureId}.
12. http://locahost:8080/products/{productId}/features?featureId={featureId}?value={string} - for change
value to {string} of the feature with id equals {featureId} of the product with id equals {productId}.
12. http://locahost:8080/products?typeId={typeId}&brandId={brandId}&title={title}&count={count}&price={price} -
for putting the product with parameters which you set as {typeId}, {brandId}, {title}, {count} and {price}.
For example, the request: localhost:8080/products?typeId=3&brandId=1&title=Super washer&count=2&price=15000
will return a response of the form:
{
    "id": 7,
    "type": {
        "id": 3,
        "title": "Стиральная машина"
    },
    "brand": {
        "id": 1,
        "title": "Samsung"
    },
    "title": "Super washer",
    "count": 2,
    "price": 15000,
    "productFeatures": []
}.
13. http://locahost:8080/products/{productId}/features/{featureId}?value={string} -
for change the feature with id which you set as {featureId} of product with id equals {productId}
to value equals {string}. For example, the request: localhost:8080/products/7/features?featureId=5&value=40x20x20
will return a response of the form:
{
    "id": 11,
    "feature": {
        "id": 5,
        "title": "Габариты"
    },
    "value": "40x20x20"
}.
14. http://locahost:8080/products/{productId}?count={count}&price={price} - for change
count or/and price for product with id equals {productId}.
15. http://locahost:8080/products/{productId}/delete - for deleting the product with id equals {productId}.