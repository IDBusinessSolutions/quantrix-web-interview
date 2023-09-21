import Orders from './orders/Order';
import { setApiImpl } from './orders/api';
import apiImpl from './orders/api-impl';

setApiImpl(apiImpl);

function App() {
  return (
    <Orders />
  );
}

export default App;
