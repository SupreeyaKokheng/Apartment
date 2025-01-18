import React from 'react';
import './Input.css';

const Input = ({ type, id, value, onChange, placeholder }) => {
  return (
    <input
      type={type}
      id={id}
      className="input"
      value={value}
      onChange={onChange}
      placeholder={placeholder}
    />
  );
};

export default Input;