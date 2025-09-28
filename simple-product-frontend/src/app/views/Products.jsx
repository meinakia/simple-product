'use client';

import React, { useCallback, useEffect, useState } from 'react';
import { getProducts, createProduct } from '../apis/simpleProductApi';

export default function Products() {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState();
  const [isLoading, setIsLoading] = useState();
  const [isAdding, setIsAdding] = useState(false);
  const [product, setProduct] = useState({
    name: '',
  });

  const loadProducts = async () => {
    setIsLoading(true);
    try {
      setProducts(await getProducts());
      setError();
    } catch (error) {
      setError(error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    loadProducts();
  }, []);

  const onSubmit = useCallback(
    async (e) => {
      e.preventDefault();
      try {
        await createProduct(product);
        loadProducts();
        setIsAdding(false);
      } catch (error) {
        setError(error);
      }
    },
    [product]
  );

  const onChange = (e) => {
    const { name, value } = e.target;

    setProduct({
      ...product,
      [name]: value,
    });
  };

  if (isLoading) {
    return <div>Loading ...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <>
      <ul>
        {products?.map((product, index) => (
          <li key={index}>{product.name}</li>
        ))}
      </ul>
      <button onClick={() => setIsAdding(true)}>Add Product</button>
      {isAdding && (
        <form onSubmit={onSubmit} id="form_product">
          <label htmlFor="name">Name</label>
          <input
            name="name"
            id="name"
            value={product?.name}
            onChange={onChange}
            required
          />
          <button type="submit">Create</button>
          <button type="button" onClick={() => setIsAdding(false)}>
            Cancel
          </button>
        </form>
      )}
    </>
  );
}
