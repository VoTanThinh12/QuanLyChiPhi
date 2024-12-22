interface Props {
  options: string[];
  id: string;
  name: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  onBlur: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  label: string;
  errors?: string;
  touched?: string;
}

const Dropdown = ({
  options,
  id,
  name,
  value,
  onChange,
  onBlur,
  label,
  errors,
  touched,
}: Props) => {
  return (
    <div className="mb-3">
      <label htmlFor="category" className="form-label">
        {label}
      </label>
      <select
        name={name}
        id={id}
        className="form-control"
        onChange={onChange}
        onBlur={onBlur}
        value={value}
      >
        <option value="" label="Select Category" />

        {options.map((option) => (
          <option key={option} value={option} label={option} />
        ))}
      </select>
      {touched && errors ? <div className="text-danger">{errors}</div> : null}
    </div>
  );
};

export default Dropdown;
