const API_BASE_URL = 'http://localhost:8080/api/v1/products';

export const getProducts = async () => {
  const resp = await fetch(API_BASE_URL);

  if (resp && resp.ok) {
    return resp.json();
  } else throw new Error('Failed to fetch products');
};

export const createProduct = async (product) => {
  const resp = await fetch(API_BASE_URL, {
    method: 'POST',
    headers: { 'Content-type': 'application/json' },
    body: JSON.stringify(product),
  });

  if (resp && resp.ok) {
    return resp.json();
  } else throw new Error('Failed to create product');
};
