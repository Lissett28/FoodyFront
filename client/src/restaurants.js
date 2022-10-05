import localforage from "localforage";
import { matchSorter } from "match-sorter";
import sortBy from "sort-by";

export async function getRestaurants(query) {
  await fakeNetwork(`getRestaurants:${query}`);
  let restaurants = await localforage.getItem("restaurants");
  if (!restaurants) restaurants = [];
  if (query) {
    restaurants = matchSorter(restaurants, query, { keys: ["first", "last"] });
  }
  return restaurants.sort(sortBy("last", "createdAt"));
}

export async function createRestaurant() {
  await fakeNetwork();
  let id = Math.random().toString(36).substring(2, 9);
  let restaurant = { id, createdAt: Date.now() };
  let restaurants = await getRestaurants();
  restaurants.unshift(restaurant);
  await set(restaurants);
  return restaurant;
}

export async function getRestaurant(id) {
  await fakeNetwork(`restaurant:${id}`);
  let restaurants = await localforage.getItem("restaurants");
  let restaurant = restaurants.find(restaurant => restaurant.id === id);
  return restaurant ?? null;
}

export async function updateRestaurant(id, updates) {
  await fakeNetwork();
  let restaurants = await localforage.getItem("restaurants");
  let restaurant = restaurants.find(restaurant => restaurant.id === id);
  if (!restaurant) throw new Error("No restaurant found for", id);
  Object.assign(restaurant, updates);
  await set(restaurants);
  return restaurant;
}

export async function deleteRestaurant(id) {
  let restaurants = await localforage.getItem("restaurants");
  let index = restaurants.findIndex(restaurant => restaurant.id === id);
  if (index > -1) {
    restaurants.splice(index, 1);
    await set(restaurants);
    return true;
  }
  return false;
}

function set(restaurants) {
  return localforage.setItem("restaurants", restaurants);
}

// fake a cache so we don't slow down stuff we've already seen
let fakeCache = {};

async function fakeNetwork(key) {
  if (!key) {
    fakeCache = {};
  }

  if (fakeCache[key]) {
    return;
  }

  fakeCache[key] = true;
  return new Promise(res => {
    setTimeout(res, Math.random() * 800);
  });
}
