// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomQuote() {
  const greetings =
      ['\"The world breaks many, but after many are strong at the broken places.\" -Ernest Hemingway',
      '\"Thomas Edison\'s last words were \"It\'s very beautiful over there.\" I don\'t know where theree is, but I believe it\'s somewhere and I hope it\'s beautiful.\" -John Green',
      '\"It\'s a very difficult era in which to be a person, just a real, actual person, instead of a collection of personality traits selected from an ednless automay of Characters.\" -Gillian Flynn',
      '\"There is always a way for those clever enough to find out.\" -Rick Riordan',
      '\"Be careful of love. It\'ll twist your brain around and leave you thinking up is down and right is wrong.\" -Rick Riordan',
      '\"...some things are better left in the past. And true things are destined to repeat themselves.\" -Suzanne Young',
      '\"We believe in ordinary acts of bravery in the courage that drives one perspn to stand up for another.\" -Veronica Roth',
      '\"I have a theory that selflessness and bravery aren\'t all that different.\" -Veronica Roth'
      ];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('quote-container');
  greetingContainer.innerText = greeting;
  console.log(greetingContainer);
}

//hello jamier function
async function getGreetingUsingAsyncAwait(){
    const response = await fetch('/data');
    const greeting = await response.text();
    document.getElementById('data-holder').innerText = greeting;
}

//arrow function
//const ArrayIn = ArrayLIst<String>;
const ArrayIn = document.getElementById('data-holder');

function getGreetingsArrowFunctions() {
  fetch('/data').then(response => response.json()).then((ArrayIn) => {
    for (i=0; i < ArrayIn.length; i++){
        console.log(ArrayIn[i]);
    }
  });
}