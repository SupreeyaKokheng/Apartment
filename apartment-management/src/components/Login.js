import React, { useState } from "react";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const data = await response.json();
                setMessage(data.message);
                console.log(data.message);
                window.location.href = "/RoomList"; // เด้งไปหน้า Welcome
            } else {
                console.log(response);
                setMessage("Invalid username or password!");
            }
        } catch (error) {
            setMessage("Something went wrong!");
        }
    };

    return (
        <div style={styles.container}>
            <h2 style={styles.heading}>Login</h2>
            <form onSubmit={handleSubmit} style={styles.form}>
            <div style={styles.inputGroup}>
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <br />
            </div>
             <div style={styles.inputGroup}>
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <br />
              </div>
                <button type="submit">Login</button>
            </form>
            <p>{message}</p>
        </div>
    );
}

const styles = {
    container: {
        textAlign: "center",
        marginTop: "100px",
        fontFamily: "'Arial', sans-serif",
    },
    heading: {
        fontSize: "2rem",
        marginBottom: "20px",
        color: "#333",
    },
    form: {
        display: "inline-block",
        backgroundColor: "#f9f9f9",
        padding: "20px",
        borderRadius: "10px",
        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
        textAlign: "left",
    },
    inputGroup: {
        marginBottom: "15px",
    },
    label: {
        display: "block",
        marginBottom: "5px",
        fontSize: "1rem",
        color: "#555",
    },
    input: {
        width: "100%",
        padding: "10px",
        border: "1px solid #ccc",
        borderRadius: "5px",
        fontSize: "1rem",
    },
    button: {
        width: "100%",
        padding: "10px",
        backgroundColor: "#007BFF",
        color: "#fff",
        border: "none",
        borderRadius: "5px",
        fontSize: "1rem",
        cursor: "pointer",
        marginTop: "10px",
        transition: "background-color 0.3s",
    },
    message: {
        marginTop: "15px",
        color: "#d9534f",
        fontWeight: "bold",
    },
};

export default Login;
