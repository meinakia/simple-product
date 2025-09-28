import Image from 'next/image';
import styles from './page.module.css';
import Products from './views/Products';

export default function Home() {
  return (
    <div className={styles.page}>
      <h1>Simple Product</h1>
      <Products />
    </div>
  );
}
