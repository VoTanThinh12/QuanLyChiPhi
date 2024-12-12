import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Dashboard from "./pages/dashboard/Dashboard";
import Login from "./pages/login/Login";
import NewExpense from "./pages/expense/NewExpense";
import ExpenseDetails from "./pages/expense/ExpenseDetails";
import ExpenseReports from "./pages/expense/ExpenseReports";
import Register from "./pages/register/Register";
const App = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/new" element={<NewExpense />} />
        <Route path="/view" element={<ExpenseDetails />} />
        <Route path="/reports" element={<ExpenseReports />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
