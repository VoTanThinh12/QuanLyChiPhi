import { Link, useParams } from "react-router-dom";
import CurrencyUtils from "../../utils/CurrencyUtils";
import DateUtils from "../../utils/DateUtils";

import useExpenseByExpenseId from "../../hooks/useExpenseByExpenseId";
const ExpenseDetails = () => {
  const { expenseId } = useParams<{ expenseId: string }>();

  // let { expenseId } = useParams<{ expenseId: string }>();
  // expenseId = undefined;
  if (!expenseId) {
    return <p className="text-danger">Invalid Expense Id</p>;
  }

  const { expenses, errors, isLoading } = useExpenseByExpenseId(expenseId);

  return (
    <div className="container mt-2">
      {isLoading && <p>Loading....</p>}
      {errors && <p className="text-danger">{errors}</p>}
      <div className="d-flex flex-row-reverse mb-2">
        <button className="btn btn-sm btn-danger">Delete</button>
        <button className="btn btn-sm btn-warning mx-2">Edit</button>
        <Link className="btn btn-sm btn-secondary" to="/">
          Back
        </Link>
      </div>
      <div className="card">
        <div className="card-body p-3">
          <table className="table table-borderless table-responsive">
            <tbody>
              <tr>
                <th>Name</th>
                <td>{expenses ? expenses.name : "N/A"}</td>
              </tr>
              <tr>
                <th>Category</th>
                <td>{expenses ? expenses.category : "N/A"}</td>
              </tr>
              <tr>
                <th>Amount</th>
                <td>
                  {expenses
                    ? CurrencyUtils.formatToVND(expenses.amount)
                    : "N/A"}
                </td>
              </tr>
              <tr>
                <th>Date</th>
                <td>
                  {expenses ? DateUtils.formatDateString(expenses.date) : "N/A"}
                </td>
              </tr>
              <tr>
                <th>Note</th>
                <td>{expenses ? expenses.note : "N/A"}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default ExpenseDetails;
