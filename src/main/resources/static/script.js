const urlInput = document.getElementById("urlInput");
const shortenButton = document.getElementById("shortenButton");
const message = document.getElementById("message");
const result = document.getElementById("result");
const shortUrl = document.getElementById("shortUrl");

shortenButton.addEventListener("click", shortenUrl);

urlInput.addEventListener("keydown", (event) => {
if (event.key === "Enter") {
shortenUrl();
}
});

async function shortenUrl() {
const longUrl = urlInput.value.trim();

message.textContent = "";
result.classList.add("hidden");

if (!longUrl) {
    message.textContent = "Please enter a URL.";
    return;
}

shortenButton.disabled = true;
shortenButton.textContent = "Shortening...";

try {
    const response = await fetch("/shorten", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            longUrl: longUrl
        })
    });

    const data = await response.json();

    if (!response.ok) {
        message.textContent = data.message || "Something went wrong.";
        return;
    }

    shortUrl.href = data.shortURL;
    shortUrl.textContent = data.shortURL;

    result.classList.remove("hidden");

} catch (error) {
    message.textContent = "Unable to connect to the server.";
} finally {
    shortenButton.disabled = false;
    shortenButton.textContent = "Shorten";
}


}