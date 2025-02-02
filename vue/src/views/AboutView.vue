<template>
  <div class="about">
    <main>
      <h3>About Indie Theatre Bulletin</h3>
      <div class="text-container">
        <p>
          Indie Theatre Bulletin is New York's new resource for learning about upcoming indie theatre. 
          It was created by artist and software developer <a href="https://linktr.ee/bryanfreedmantech" target="_blank" rel="noopener noreferrer">
                Bryan Freedman</a>. You can donate to him using <a href="https://ko-fi.com/bryanfreedman" target="_blank" rel="noopener noreferrer">
                Ko-Fi</a>! Thank you so much for your support to keep this resource running!
        </p>
        <p>
          You can browse upcoming theatre or submit your own piece. Please allow a day for your listing to be moderated and go live. 
          If there are any issues, you can email xxxx and we would be happy to make any adjustments!
        </p>
      </div>
    </main>
  </div>
</template>

<style scoped>
.about {
padding: 8px 16px;
margin: 0 auto;
max-width: 1200px;
box-sizing: border-box;
}

main {
margin-top: 16px;
}

/* 🎭 Masking tape effect on heading */
h3 {
font-size: 1.75rem;
margin-bottom: 12px;
padding: 12px 16px;
background-image: url('@/assets/images/masking-tape-crop.png'); /* Apply tape effect */
/* background-size: contain; Scale correctly */
background-repeat: no-repeat;
background-position: center;
display: inline-block;
}

/* 🎨 White Background for Text */
.text-container {
background-color: white; /* White background */
padding: 24px; /* Space inside */
/* border-radius: 8px; Slight rounding */
box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
margin: 16px auto; /* Center and add spacing */
max-width: 800px; /* Keep it a readable width */
}

.text-container p {
line-height: 1.6;
margin-bottom: 12px; /* Adds spacing between paragraphs */
}
</style>
