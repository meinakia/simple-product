import React from 'react';
import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import * as api from '../../apis/simpleProductApi';
import Products from '../Products';

jest.mock('../../apis/simpleProductApi');

describe('product view', () => {
  const mockProducts = [
    {
      name: 'product 1',
    },
    { name: 'product 2' },
  ];

  beforeEach(() => {
    jest.resetAllMocks();
  });

  it('shows loading initially and products', async () => {
    api.getProducts.mockResolvedValue(mockProducts);

    render(<Products />);

    expect(screen.getByText('Loading ...')).toBeInTheDocument();

    await waitFor(() => {
      expect(screen.queryByText('Loading ...')).not.toBeInTheDocument();
      expect(screen.getByText('product 1')).toBeInTheDocument();
      expect(screen.getByText('product 2')).toBeInTheDocument();
    });
  });

  it('should createProduct when submitting form', async () => {
    const newProduct = { name: 'New Product' };
    api.getProducts.mockResolvedValue(mockProducts);
    api.createProduct = jest.fn().mockResolvedValue(newProduct);

    render(<Products />);

    await waitFor(() => {
      expect(screen.getByText('Add Product')).toBeInTheDocument();
      fireEvent.click(screen.getByText('Add Product'));
    });
    await waitFor(() => {
      const input = screen.getByLabelText('Name');
      fireEvent.change(input, { target: { value: newProduct.name } });

      fireEvent.click(screen.getByText('Create'));

      expect(api.createProduct).toHaveBeenCalledWith(newProduct);
    });
  });
});
